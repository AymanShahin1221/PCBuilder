package com.app.service.db;

import com.app.entity.model.*;
import com.app.service.kafka.KafkaProducerService;
import com.app.service.util.DBUtils;
import jakarta.annotation.PreDestroy;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.sql.*;
import com.app.service.util.JsonUtils;
import java.util.List;
import java.util.UUID;

import static com.app.service.util.DBUtils.initDBConnection;

@Service
public class DBUpdateService {

    /**
     * Service class responsible for performing daily database update (currently set to update everyday at 12 am --> daily_db_update=0 0 0 * * *
     * Parses JSON data retrieved from https://github.com/docyx/pc-part-dataset/tree/main/data/json and upserts parts into their respective tables
     * Since the name column is marked as unique, the upsert will update conflicting records with new values
     */

    private final Connection connection = initDBConnection();
    private final KafkaProducerService kafkaProducerService;

    private static final Logger logger = LoggerFactory.getLogger(DBUpdateService.class);

    @Autowired
    public DBUpdateService(KafkaProducerService kafkaProducerService) throws SQLException {
        connection.setAutoCommit(false);
        this.kafkaProducerService = kafkaProducerService;
    }

    public void upsertCPUTable(CPU cpu) throws SQLException {
        PreparedStatement preparedStatement = null;
        try
        {
            String stmt = "INSERT INTO CPU (pid, boost_clock, core_clock, core_count, graphics, name, price, smt, tdp) " +
                          "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                          "ON CONFLICT (name) DO UPDATE " +
                          "SET boost_clock = EXCLUDED.boost_clock, core_clock = EXCLUDED.core_clock, " +
                          "core_count = EXCLUDED.core_count, graphics = EXCLUDED.graphics, name = EXCLUDED.name, " +
                          "price = CASE WHEN EXCLUDED.price IS NOT NULL THEN EXCLUDED.price ELSE CPU.price END, smt = EXCLUDED.smt, tdp = EXCLUDED.tdp";

            preparedStatement = connection.prepareStatement(stmt);

            preparedStatement.setObject(1, cpu.getPid());
            preparedStatement.setObject(2, cpu.getBoost_clock());
            preparedStatement.setObject(3, cpu.getCore_clock());
            preparedStatement.setObject(4, cpu.getCore_count());
            preparedStatement.setObject(5, cpu.getGraphics());
            preparedStatement.setObject(6, cpu.getName());
            preparedStatement.setObject(7, cpu.getPrice());
            preparedStatement.setObject(8, cpu.getSmt());
            preparedStatement.setObject(9, cpu.getTdp());

            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            logger.error("Error while updating table CPU");
        }
        finally
        {
            DBUtils.close(preparedStatement);
        }
    }

    public void upsertGPUTable(GPU gpu) throws SQLException {
        PreparedStatement preparedStatement = null;
        try
        {
            String stmt = "INSERT INTO GPU (pid, boost_clock, chipset, color, core_clock, length, memory, name, price) " +
                          "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                          "ON CONFLICT (name) DO UPDATE " +
                          "SET boost_clock = EXCLUDED.boost_clock, chipset = EXCLUDED.chipset, " +
                          "color = EXCLUDED.color, core_clock = EXCLUDED.core_clock, length = EXCLUDED.length, " +
                          "memory = EXCLUDED.memory, name = EXCLUDED.name, price = CASE WHEN EXCLUDED.price IS NOT NULL THEN EXCLUDED.price ELSE GPU.price END";

            preparedStatement = connection.prepareStatement(stmt);

            preparedStatement.setObject(1, gpu.getPid());
            preparedStatement.setObject(2, gpu.getBoost_clock());
            preparedStatement.setObject(3, gpu.getChipset());
            preparedStatement.setObject(4, gpu.getColor());
            preparedStatement.setObject(5, gpu.getCore_clock());
            preparedStatement.setObject(6, gpu.getLength());
            preparedStatement.setObject(7, gpu.getMemory());
            preparedStatement.setObject(8, gpu.getName());
            preparedStatement.setObject(9, gpu.getPrice());

            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            logger.error("Error while updating table GPU");
        }
        finally
        {
            DBUtils.close(preparedStatement);
        }
    }

    public void upsertCaseTable(Case case_) throws SQLException {
        PreparedStatement preparedStatement = null;
        try
        {
            String stmt = "INSERT INTO cases (pid, color, external_volume, internal_35_bays, name, price, psu, side_panel, type) " +
                          "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                          "ON CONFLICT (name) DO UPDATE " +
                          "SET color = EXCLUDED.color, external_volume = EXCLUDED.external_volume, " +
                          "internal_35_bays = EXCLUDED.internal_35_bays, name = EXCLUDED.name, price = CASE WHEN EXCLUDED.price IS NOT NULL THEN EXCLUDED.price ELSE cases.price END, " +
                          "psu = EXCLUDED.psu, side_panel = EXCLUDED.side_panel, type = EXCLUDED.type";

            preparedStatement = connection.prepareStatement(stmt);

            preparedStatement.setObject(1, case_.getPid());
            preparedStatement.setObject(2, case_.getColor());
            preparedStatement.setObject(3, case_.getExternal_volume());
            preparedStatement.setObject(4, case_.getInternal_35_bays());
            preparedStatement.setObject(5, case_.getName());
            preparedStatement.setObject(6, case_.getPrice());
            preparedStatement.setObject(7, case_.isPsu());
            preparedStatement.setObject(8, case_.getSidePanel());
            preparedStatement.setObject(9, case_.getType());

            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            logger.error("Error while updating table Case");
        }
        finally
        {
            DBUtils.close(preparedStatement);
        }
    }

    public void upsertCoolerTable(Cooler cooler) throws SQLException {
        PreparedStatement preparedStatement = null;
        try
        {
            String stmt = "INSERT INTO COOLER (pid, color, name, price, size) " +
                          "VALUES (?, ?, ?, ?, ?) " +
                          "ON CONFLICT (name) DO UPDATE " +
                          "SET color = EXCLUDED.color, name = EXCLUDED.name, " +
                          "price = CASE WHEN EXCLUDED.price IS NOT NULL THEN EXCLUDED.price ELSE COOLER.price END, " +
                          "size = EXCLUDED.size";

            preparedStatement = connection.prepareStatement(stmt);

            preparedStatement.setObject(1, cooler.getPid());
            preparedStatement.setObject(2, cooler.getColor());
            preparedStatement.setObject(3, cooler.getName());
            preparedStatement.setObject(4, cooler.getPrice());
            preparedStatement.setObject(5, cooler.getSize());

            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            logger.error("Error while updating table Cooler");
        }
        finally
        {
            DBUtils.close(preparedStatement);
        }
    }

    public void upsertKeyboardTable(Keyboard keyboard) throws SQLException {
        PreparedStatement preparedStatement = null;
        try
        {
            String stmt = "INSERT INTO KEYBOARD (pid, backlit, color, connection_type, name, price, style, switches, tenkeyless) " +
                          "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                          "ON CONFLICT (name) DO UPDATE " +
                          "SET backlit = EXCLUDED.backlit, color = EXCLUDED.color, " +
                          "connection_type = EXCLUDED.connection_type, name = EXCLUDED.name, price = CASE WHEN EXCLUDED.price IS NOT NULL THEN EXCLUDED.price ELSE KEYBOARD.price END, " +
                          "style = EXCLUDED.style, switches = EXCLUDED.switches, tenkeyless = EXCLUDED.tenkeyless";

            preparedStatement = connection.prepareStatement(stmt);

            preparedStatement.setObject(1, keyboard.getPid());
            preparedStatement.setObject(2, keyboard.getBacklit());
            preparedStatement.setObject(3, keyboard.getColor());
            preparedStatement.setObject(4, keyboard.getConnection_type());
            preparedStatement.setObject(5, keyboard.getName());
            preparedStatement.setObject(6, keyboard.getPrice());
            preparedStatement.setObject(7, keyboard.getStyle());
            preparedStatement.setObject(8, keyboard.getSwitches());
            preparedStatement.setObject(9, keyboard.isTenkeyless());

            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            logger.error("Error while updating table Keyboard");
        }
        finally
        {
            DBUtils.close(preparedStatement);
        }
    }

    public void upsertMemoryTable(Memory memory) throws SQLException {
        PreparedStatement preparedStatement = null;
        try
        {
            String stmt = "INSERT INTO MEMORY (pid, cas_latency, color, first_word_latency, modules, name, price, price_per_gb, speed) " +
                          "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                          "ON CONFLICT (name) DO UPDATE " +
                          "SET cas_latency = EXCLUDED.cas_latency, color = EXCLUDED.color, " +
                          "first_word_latency = EXCLUDED.first_word_latency, modules = EXCLUDED.modules, name = EXCLUDED.name, " +
                          "price = CASE WHEN EXCLUDED.price IS NOT NULL THEN EXCLUDED.price ELSE MEMORY.price END, price_per_gb = EXCLUDED.price_per_gb, speed = EXCLUDED.speed";

            preparedStatement = connection.prepareStatement(stmt);

            preparedStatement.setObject(1, memory.getPid());
            preparedStatement.setObject(2, memory.getCas_latency());
            preparedStatement.setObject(3, memory.getColor());
            preparedStatement.setObject(4, memory.getFirst_word_latency());

            Array modulesArray = connection.createArrayOf("INTEGER", memory.getModules().toArray());
            preparedStatement.setArray(5, modulesArray);

            preparedStatement.setObject(6, memory.getName());
            preparedStatement.setObject(7, memory.getPrice());
            preparedStatement.setObject(8, memory.getPrice_per_gb());

            preparedStatement.setObject(9, memory.getSpeed());

            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            logger.error("Error while updating table Memory");
        }
        finally
        {
            DBUtils.close(preparedStatement);
        }
    }

    public void upsertMotherboardTable(Motherboard motherboard) throws SQLException {
        PreparedStatement preparedStatement = null;
        try
        {
            String stmt = "INSERT INTO MOTHERBOARD (pid, color, form_factor, max_memory, memory_slots, name, price, socket) " +
                          "VALUES (?, ?, ?, ?, ?, ?, ?, ?) " +
                          "ON CONFLICT (name) DO UPDATE " +
                          "SET color = EXCLUDED.color, form_factor = EXCLUDED.form_factor, " +
                          "max_memory = EXCLUDED.max_memory, memory_slots = EXCLUDED.memory_slots, name = EXCLUDED.name, " +
                          "price = CASE WHEN EXCLUDED.price IS NOT NULL THEN EXCLUDED.price ELSE MOTHERBOARD.price END, socket = EXCLUDED.socket";

            preparedStatement = connection.prepareStatement(stmt);

            preparedStatement.setObject(1, motherboard.getPid());
            preparedStatement.setObject(2, motherboard.getColor());
            preparedStatement.setObject(3, motherboard.getForm_factor());
            preparedStatement.setObject(4, motherboard.getMax_memory());
            preparedStatement.setObject(5, motherboard.getMemory_slots());
            preparedStatement.setObject(6, motherboard.getName());
            preparedStatement.setObject(7, motherboard.getPrice());
            preparedStatement.setObject(8, motherboard.getSocket());

            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            logger.error("Error while updating table Motherboard");
        }
        finally
        {
            DBUtils.close(preparedStatement);
        }
    }

    public void upsertMonitorTable(Monitor monitor) throws SQLException {
        PreparedStatement preparedStatement = null;
        try
        {
            String stmt = "INSERT INTO MONITOR (pid, aspect_ratio, name, panel_type, price, refresh_rate, resolution, response_time, screen_size) " +
                          "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                          "ON CONFLICT (name) DO UPDATE " +
                          "SET aspect_ratio = EXCLUDED.aspect_ratio, name = EXCLUDED.name, " +
                          "panel_type = EXCLUDED.panel_type, price = CASE WHEN EXCLUDED.price IS NOT NULL THEN EXCLUDED.price ELSE MONITOR.price END, refresh_rate = EXCLUDED.refresh_rate, " +
                          "resolution = EXCLUDED.resolution, response_time = EXCLUDED.response_time, screen_size = EXCLUDED.screen_size";

            preparedStatement = connection.prepareStatement(stmt);

            preparedStatement.setObject(1, monitor.getPid());
            preparedStatement.setObject(2, monitor.getAspect_ratio());
            preparedStatement.setObject(3, monitor.getName());
            preparedStatement.setObject(4, monitor.getPanel_type());
            preparedStatement.setObject(5, monitor.getPrice());
            preparedStatement.setObject(6, monitor.getRefresh_rate());
            preparedStatement.setArray(7, connection.createArrayOf("INTEGER", monitor.getResolution().toArray()));
            preparedStatement.setObject(8, monitor.getResponse_time());
            preparedStatement.setObject(9, monitor.getScreen_size());

            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            logger.error("Error while updating table Monitor");
        }
        finally
        {
            DBUtils.close(preparedStatement);
        }
    }

    public void upsertOSTable(OS os) throws SQLException {
        PreparedStatement preparedStatement = null;
        try
        {
            String stmt = "INSERT INTO OS (pid, max_memory, name, price) " +
                          "VALUES (?, ?, ?, ?) " +
                          "ON CONFLICT (name) DO UPDATE " +
                          "SET max_memory = EXCLUDED.max_memory, " +
                          "name = EXCLUDED.name, price = CASE WHEN EXCLUDED.price IS NOT NULL THEN EXCLUDED.price ELSE OS.price END";

            preparedStatement = connection.prepareStatement(stmt);

            preparedStatement.setObject(1, os.getPid());
            preparedStatement.setObject(2, os.getMax_memory());
            preparedStatement.setObject(3, os.getName());
            preparedStatement.setObject(4, os.getPrice());

            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            logger.error("Error while updating table OS");
        }
        finally
        {
            DBUtils.close(preparedStatement);
        }
    }

    public void upsertPowerSupplyTable(PowerSupply powerSupply) throws SQLException {
        PreparedStatement preparedStatement = null;
        try
        {
            String stmt = "INSERT INTO POWER_SUPPLY (pid, color, efficiency, modular, name, price, type, wattage) " +
                          "VALUES (?, ?, ?, ?, ?, ?, ?, ?) " +
                          "ON CONFLICT (name) DO UPDATE " +
                          "SET color = EXCLUDED.color, efficiency = EXCLUDED.efficiency, " +
                          "modular = EXCLUDED.modular, name = EXCLUDED.name, price = CASE WHEN EXCLUDED.price IS NOT NULL THEN EXCLUDED.price ELSE POWER_SUPPLY.price END, " +
                          "type = EXCLUDED.type, wattage = EXCLUDED.wattage";

            preparedStatement = connection.prepareStatement(stmt);

            preparedStatement.setObject(1, powerSupply.getPid());
            preparedStatement.setObject(2, powerSupply.getColor());
            preparedStatement.setObject(3, powerSupply.getEfficiency());
            preparedStatement.setObject(4, powerSupply.getModular());
            preparedStatement.setObject(5, powerSupply.getName());
            preparedStatement.setObject(6, powerSupply.getPrice());
            preparedStatement.setObject(7, powerSupply.getType());
            preparedStatement.setObject(8, powerSupply.getWattage());

            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            logger.error("Error while updating table Power_Supply");
        }
        finally
        {
            DBUtils.close(preparedStatement);
        }
    }

    public void upsertStorageTable(Storage storage) throws SQLException {
        PreparedStatement preparedStatement = null;
        try
        {
            String stmt = "INSERT INTO STORAGE (pid, cache, capacity, form_factor, interface_, name, price, price_per_gb, type) " +
                          "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                          "ON CONFLICT (name) DO UPDATE " +
                          "SET cache = EXCLUDED.cache, capacity = EXCLUDED.capacity, " +
                          "form_factor = EXCLUDED.form_factor, interface_ = EXCLUDED.interface_, name = EXCLUDED.name, " +
                          "price = CASE WHEN EXCLUDED.price IS NOT NULL THEN EXCLUDED.price ELSE STORAGE.price END, price_per_gb = EXCLUDED.price_per_gb, type = EXCLUDED.type";

            preparedStatement = connection.prepareStatement(stmt);

            preparedStatement.setObject(1, storage.getPid());
            preparedStatement.setObject(2, storage.getCache());
            preparedStatement.setObject(3, storage.getCapacity());
            preparedStatement.setObject(4, storage.getForm_factor());
            preparedStatement.setObject(5, storage.getInterface_());
            preparedStatement.setObject(6, storage.getName());
            preparedStatement.setObject(7, storage.getPrice());
            preparedStatement.setObject(8, storage.getPrice_per_gb());
            preparedStatement.setObject(9, storage.getType());

            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            logger.error("Error while updating table Storage");
        }
        finally
        {
            DBUtils.close(preparedStatement);
        }
    }

    @Scheduled(cron="${daily_db_update}")
    private void processCPUData() {

        String jsonData = JSONDataService.ProductCategory.fetchJsonData(JSONDataService.ProductCategory.CPU);
        JSONArray jsonArray = JsonUtils.stringToJSONArray(jsonData);

        try
        {
            for (int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                UUID pid = UUID.randomUUID();
                String name = jsonObject.isNull("name") ? null : jsonObject.getString("name");
                Double price = jsonObject.isNull("price") ? null : jsonObject.getDouble("price");
                Integer core_count = jsonObject.isNull("core_count") ? null : jsonObject.getInt("core_count");
                Double core_clock = jsonObject.isNull("core_clock") ? null : jsonObject.getDouble("core_clock");
                Double boost_clock = jsonObject.isNull("boost_clock") ? null : jsonObject.getDouble("boost_clock");
                Integer tdp = jsonObject.isNull("tdp") ? null : jsonObject.getInt("tdp");
                String graphics = jsonObject.isNull("graphics") ? null : jsonObject.getString("graphics");
                Boolean stmt = jsonObject.isNull("smt") ? null : jsonObject.getBoolean("smt");

                CPU cpu = new CPU(pid, name, price, core_count, core_clock, boost_clock, tdp, graphics, stmt);
                kafkaProducerService.send("cpuPartsTopic", cpu);
            }
        }
        catch (JSONException e)
        {
            logger.error("Error parsing CPU parts JSON Data");
        }
        logger.info("Successfully updated table CPU");
    }

    @Scheduled(cron="${daily_db_update}")
    private void processGPUData() {

        String jsonData = JSONDataService.ProductCategory.fetchJsonData(JSONDataService.ProductCategory.GPU);
        JSONArray jsonArray = JsonUtils.stringToJSONArray(jsonData);

        try
        {
            for(int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                UUID pid = UUID.randomUUID();
                Integer boost_clock = jsonObject.isNull("boost_clock") ? null : jsonObject.getInt("boost_clock");
                String chipset = jsonObject.isNull("chipset") ? null : jsonObject.getString("chipset");
                String color = jsonObject.isNull("color") ? null : jsonObject.getString("color");
                Integer core_clock = jsonObject.isNull("core_clock") ? null : jsonObject.getInt("core_clock");
                Integer length = jsonObject.isNull("length") ? null : jsonObject.getInt("length");
                Integer memory = jsonObject.isNull("memory") ? null : jsonObject.getInt("memory");
                String name = jsonObject.isNull("name") ? null : jsonObject.getString("name");
                Double price = jsonObject.isNull("price") ? null : jsonObject.getDouble("price");

                GPU gpu = new GPU(pid, name, price, chipset, memory, core_clock, boost_clock, color, length);
                kafkaProducerService.send("gpuPartsTopic", gpu);
            }
        }
        catch(JSONException e)
        {
            logger.error("Error parsing GPU parts JSON Data");
        }
        logger.info("Successfully updated table GPU");
    }

    @Scheduled(cron="${daily_db_update}")
    private void processCaseData() {
        
        String jsonData = JSONDataService.ProductCategory.fetchJsonData(JSONDataService.ProductCategory.CASE);
        JSONArray jsonArray = JsonUtils.stringToJSONArray(jsonData);

        try
        {
            for(int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                UUID pid = UUID.randomUUID();
                String color = jsonObject.isNull("color") ? null : jsonObject.getString("color");
                Double external_volume = jsonObject.isNull("external_volume") ? null : jsonObject.getDouble("external_volume");
                Integer internal_35_bays = jsonObject.isNull("internal_35_bays") ? null : jsonObject.getInt("internal_35_bays");
                String name = jsonObject.isNull("name") ? null : jsonObject.getString("name");
                Double price = jsonObject.isNull("price") ? null : jsonObject.getDouble("price");
                Integer psu = jsonObject.isNull("psu") ? null : jsonObject.getInt("psu");
                String sidePanel = jsonObject.isNull("sidePanel") ? null : jsonObject.getString("sidePanel");
                String type = jsonObject.isNull("type") ? null : jsonObject.getString("type");

                Case case_ = new Case(pid, name, price, type, color, psu, sidePanel, external_volume, internal_35_bays);
                kafkaProducerService.send("casePartsTopic", case_);
            }
        }
        catch(JSONException e)
        {
            logger.error("Error parsing Case parts JSON Data");
        }
        logger.info("Successfully updated table Case");
    }

    @Scheduled(cron="${daily_db_update}")
    private void processCoolerData() {

        String jsonData = JSONDataService.ProductCategory.fetchJsonData(JSONDataService.ProductCategory.COOLER);
        JSONArray jsonArray = JsonUtils.stringToJSONArray(jsonData);

        try
        {
            for(int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                UUID pid = UUID.randomUUID();
                String color = jsonObject.isNull("color") ? null : jsonObject.getString("color");
                String name = jsonObject.isNull("name") ? null : jsonObject.getString("name");
                Double price = jsonObject.isNull("price") ? null : jsonObject.getDouble("price");
                Integer size = jsonObject.isNull("size") ? null : jsonObject.getInt("size");
                
                Cooler cooler = new Cooler(pid, name, price, color, size);
                kafkaProducerService.send("coolerPartsTopic", cooler);
            }
        }
        catch(JSONException e)
        {
            logger.error("Error parsing Cooler parts JSON Data");
        }
        logger.info("Successfully updated table Cooler");
    }

    @Scheduled(cron="${daily_db_update}")
    private void processKeyboardData() {

        String jsonData = JSONDataService.ProductCategory.fetchJsonData(JSONDataService.ProductCategory.KEYBOARD);
        JSONArray jsonArray = JsonUtils.stringToJSONArray(jsonData);

        try
        {
            for(int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                UUID pid = UUID.randomUUID();
                String backlit = jsonObject.isNull("backlit") ? null : jsonObject.getString("backlit");
                String color = jsonObject.isNull("color") ? null : jsonObject.getString("color");
                String connection_type = jsonObject.isNull("connection_type") ? null : jsonObject.getString("connection_type");
                String name = jsonObject.isNull("name") ? null : jsonObject.getString("name");
                Double price = jsonObject.isNull("price") ? null : jsonObject.getDouble("price");
                String style = jsonObject.isNull("style") ? null : jsonObject.getString("style");
                String switches = jsonObject.isNull("switches") ? null : jsonObject.getString("switches");
                Boolean tenkeyless = jsonObject.isNull("tenkeyless") ? null : jsonObject.getBoolean("tenkeyless");

                Keyboard keyboard = new Keyboard(pid, name, price, style, switches, backlit, tenkeyless, connection_type, color);
                kafkaProducerService.send("keyboardPartsTopic", keyboard);
            }
        }
        catch(JSONException e)
        {
            logger.error("Error parsing Keyboard parts JSON Data");
        }
        logger.info("Successfully updated table Keyboard");
    }

    @Scheduled(cron="${daily_db_update}")
    private void processMemoryData() {

        String jsonData = JSONDataService.ProductCategory.fetchJsonData(JSONDataService.ProductCategory.MEMORY);
        JSONArray jsonArray = JsonUtils.stringToJSONArray(jsonData);

        try
        {
            for(int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                UUID pid = UUID.randomUUID();
                Integer cas_latency = jsonObject.isNull("cas_latency") ? null : jsonObject.getInt("cas_latency");
                String color = jsonObject.isNull("color") ? null : jsonObject.getString("color");
                Double first_word_latency = jsonObject.isNull("cas_latency") ? null : jsonObject.getDouble("first_word_latency");
                List<Integer> modules = jsonObject.isNull("modules") ? null : JsonUtils.getIntegerArrayList(jsonObject.getJSONArray("modules"));
                String name = jsonObject.isNull("name") ? null : jsonObject.getString("name");
                Double price = jsonObject.isNull("price") ? null : jsonObject.getDouble("price");
                Double price_per_gb = jsonObject.isNull("price_per_gb") ? null : jsonObject.getDouble("price_per_gb");

                Integer speed = null;
                if (jsonObject.has("speed") && jsonObject.get("speed") instanceof JSONArray)
                {
                    JSONArray speedArray = jsonObject.getJSONArray("speed");
                    if (!speedArray.isEmpty())
                    {
                        int[] speeds = new int[speedArray.length()];
                        for (int j = 0; j < speedArray.length(); j++)
                            speeds[j] = speedArray.getInt(j);

                        speed = speeds[1];
                    }
                    else
                        speed = jsonObject.isNull("speed") ? null : jsonObject.getInt("speed");
                }

                Memory memory = new Memory(pid, name, price, speed, modules, price_per_gb, color, first_word_latency, cas_latency);
                kafkaProducerService.send("memoryPartsTopic", memory);
            }
        }
        catch(JSONException e)
        {
            logger.error("Error parsing Memory parts JSON Data");
        }
        logger.info("Successfully updated table Memory");
    }

    @Scheduled(cron="${daily_db_update}")
    private void processMonitorData() {

        String jsonData = JSONDataService.ProductCategory.fetchJsonData(JSONDataService.ProductCategory.MONITOR);
        JSONArray jsonArray = JsonUtils.stringToJSONArray(jsonData);

        try
        {
            for(int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                UUID pid = UUID.randomUUID();
                String aspect_ratio = jsonObject.isNull("aspect_ratio") ? null : jsonObject.getString("aspect_ratio");
                String name = jsonObject.isNull("name") ? null : jsonObject.getString("name");
                String panel_type = jsonObject.isNull("panel_type") ? null : jsonObject.getString("panel_type");
                Double price = jsonObject.isNull("price") ? null : jsonObject.getDouble("price");
                Integer refresh_rate = jsonObject.isNull("refresh_rate") ? null : jsonObject.getInt("refresh_rate");
                List<Integer> resolution = jsonObject.isNull("resolution") ? null : JsonUtils.getIntegerArrayList(jsonObject.getJSONArray("resolution"));
                Double response_time = jsonObject.isNull("response_time") ? null : jsonObject.getDouble("response_time");
                Double screen_size = jsonObject.isNull("screen_size") ? null : jsonObject.getDouble("screen_size");

                Monitor monitor = new Monitor(pid, name, price, screen_size, resolution, refresh_rate, response_time, panel_type, aspect_ratio);
                kafkaProducerService.send("monitorPartsTopic", monitor);
            }
        }
        catch(JSONException e)
        {
            logger.error("Error parsing Monitor parts JSON Data");
        }
        logger.info("Successfully updated table Monitor");
    }

    @Scheduled(cron="${daily_db_update}")
    private void processMotherboardData() {

        String jsonData = JSONDataService.ProductCategory.fetchJsonData(JSONDataService.ProductCategory.MOTHERBOARD);
        JSONArray jsonArray = JsonUtils.stringToJSONArray(jsonData);

        try
        {
            for(int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                UUID pid = UUID.randomUUID();
                String color = jsonObject.isNull("color") ? null : jsonObject.getString("color");
                String form_factor = jsonObject.isNull("form_factor") ? null : jsonObject.getString("form_factor");
                Integer max_memory = jsonObject.isNull("max_memory") ? null : jsonObject.getInt("max_memory");
                Integer memory_slots = jsonObject.isNull("memory_slots") ? null : jsonObject.getInt("memory_slots");
                String name = jsonObject.isNull("name") ? null : jsonObject.getString("name");
                Double price = jsonObject.isNull("price") ? null : jsonObject.getDouble("price");
                String socket = jsonObject.isNull("socket") ? null : jsonObject.getString("socket");

                Motherboard motherboard = new Motherboard(pid, name, price, socket, form_factor, max_memory, memory_slots, color);
                kafkaProducerService.send("motherboardPartsTopic", motherboard);
            }
        }
        catch(JSONException e)
        {
            logger.error("Error parsing Motherboard parts JSON Data");
        }
        logger.info("Successfully updated table Motherboard");
    }

    @Scheduled(cron="${daily_db_update}")
    private void processOSData() {
        
        String jsonData = JSONDataService.ProductCategory.fetchJsonData(JSONDataService.ProductCategory.OS);
        JSONArray jsonArray = JsonUtils.stringToJSONArray(jsonData);

        try
        {
            for(int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                UUID pid = UUID.randomUUID();
                Integer max_memory = jsonObject.isNull("max_memory") ? null : jsonObject.getInt("max_memory");
                String name = jsonObject.isNull("name") ? null : jsonObject.getString("name");
                Double price = jsonObject.isNull("price") ? null : jsonObject.getDouble("price");

                OS os = new OS(pid, name, price, max_memory);
                kafkaProducerService.send("osPartsTopic", os);
            }
        }
        catch(JSONException e)
        {
            logger.error("Error parsing OS parts JSON Data");
        }
        logger.info("Successfully updated table OS");
    }

    @Scheduled(cron="${daily_db_update}")
    private void processPowerSupplyData() {

        String jsonData = JSONDataService.ProductCategory.fetchJsonData(JSONDataService.ProductCategory.POWER_SUPPLY);
        JSONArray jsonArray = JsonUtils.stringToJSONArray(jsonData);

        try
        {
            for(int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                UUID pid = UUID.randomUUID();
                String color = jsonObject.isNull("color") ? null : jsonObject.getString("color");
                String efficiency = jsonObject.isNull("efficiency") ? null : jsonObject.getString("efficiency");

                String modular;
                if (jsonObject.isNull("modular"))
                    modular = null;
                else
                {
                    Object modularValue = jsonObject.get("modular");
                    if (modularValue instanceof Boolean)
                        modular = String.valueOf((boolean) modularValue);
                    else
                        modular = jsonObject.getString("modular");
                }

                String name = jsonObject.isNull("name") ? null : jsonObject.getString("name");
                Double price = jsonObject.isNull("price") ? null : jsonObject.getDouble("price");
                String type = jsonObject.isNull("type") ? null : jsonObject.getString("type");
                Integer wattage = jsonObject.isNull("wattage") ? null : jsonObject.getInt("wattage");

                PowerSupply powerSupply = new PowerSupply(pid, name, price, type, efficiency, wattage, modular, color);
                kafkaProducerService.send("powerSupplyPartsTopic", powerSupply);
            }
        }
        catch(JSONException e)
        {
            logger.error("Error parsing Power Supply parts JSON Data");
        }
        logger.info("Successfully updated table Power_Supply");
    }

    @Scheduled(cron="${daily_db_update}")
    private void processStorageData() {

        String jsonData = JSONDataService.ProductCategory.fetchJsonData(JSONDataService.ProductCategory.STORAGE);
        JSONArray jsonArray = JsonUtils.stringToJSONArray(jsonData);

        try
        {
            for(int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                UUID pid = UUID.randomUUID();
                Integer cache = jsonObject.isNull("cache") ? null : jsonObject.getInt("cache");
                Integer capacity = jsonObject.isNull("capacity") ? null : jsonObject.getInt("capacity");

                String form_factor;
                if(jsonObject.isNull("form_factor"))
                    form_factor = null;
                else
                {
                    Object form_factor_value = jsonObject.get("form_factor");
                    if(form_factor_value instanceof BigDecimal)
                        form_factor = String.valueOf(form_factor_value);
                    else
                        form_factor = jsonObject.getString("form_factor");
                }

                String interface_ = jsonObject.isNull("interface_") ? null : jsonObject.getString("interface_");
                String name = jsonObject.isNull("name") ? null : jsonObject.getString("name");
                Double price = jsonObject.isNull("price") ? null : jsonObject.getDouble("price");
                Double price_per_gb = jsonObject.isNull("price_per_gb") ? null : jsonObject.getDouble("price_per_gb");

                String type;
                if(jsonObject.isNull("type"))
                    type = null;
                else
                {
                    Object type_value = jsonObject.get("type");
                    if(type_value instanceof Integer)
                        type = String.valueOf(type_value);
                    else
                        type = jsonObject.getString("type");
                }

                Storage storage = new Storage(pid, name, price, capacity, price_per_gb, type, cache, form_factor, interface_);
                kafkaProducerService.send("storagePartsTopic", storage);
            }
        }
        catch(JSONException e)
        {
            logger.error("Error parsing Storage parts JSON Data");
        }
        logger.info("Successfully updated table Storage");
    }

    @PreDestroy
    public void closeDBConnection() {
        try
        {
            if (connection != null) {
                logger.info("Closing database connection...");
                connection.close();
                logger.info("Closed database connection.");
            }
        }
        catch (SQLException e)
        {
            logger.error("Error closing database connection");
        }
    }
}
