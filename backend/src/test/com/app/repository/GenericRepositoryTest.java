package com.app.repository;

import com.app.entity.model.CPU;
import com.app.entity.model.GPU;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ComponentScan("com.app.repository")
class GenericRepositoryTest
{

    @Autowired
    private GenericRepository genericRepository;

    @PersistenceContext
    private EntityManager entityManager;


    private UUID generateRandomUUID()
    {
        return UUID.randomUUID();
    }

    private static String generateRandomString(int n)
    {

        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";


        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++)
        {
            int index = (int) (AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }

    private static Double generateRandomDouble()
    {
        Random random = new Random();
        return random.nextDouble();
    }

    private static int generateRandomInteger()
    {
        Random random = new Random();
        return random.nextInt();
    }

    @BeforeEach
    void setUp()
    {

        for (int i = 0; i < 35; i++)
        {
            entityManager.persist(
                    new CPU(generateRandomUUID(), "intel core i9-14" + generateRandomString(5), generateRandomDouble(),
                            generateRandomInteger(), generateRandomDouble(),
                            generateRandomDouble(), generateRandomInteger(),
                            generateRandomString(12), true)
            );
        }

        for (int i = 0; i < 35; i++)
        {
            entityManager.persist(
                    new GPU(generateRandomUUID(), "GeForce rtx 40" + generateRandomString(5), generateRandomDouble(),
                            "GeForce rtx 40" + generateRandomString(5), generateRandomInteger(),
                            generateRandomInteger(), generateRandomInteger(),
                            generateRandomString(12), generateRandomInteger())
            );
        }

        for (int i = 0; i < 926; i++)
        {
            CPU cpu = new CPU(generateRandomUUID(), generateRandomString(12), generateRandomDouble(),
                    generateRandomInteger(), generateRandomDouble(),
                    generateRandomDouble(), generateRandomInteger(),
                    generateRandomString(12), true);

            entityManager.persist(cpu);
            entityManager.flush();
        }
    }

    @Test
    void testGetAllPartsByCategory()
    {
        JSONArray parts = genericRepository.getAllPartsByCategory(CPU.class);
        assertNotNull(parts);
    }

    @Test
    void findProductsBySearchTerm()
    {
        JSONObject jsonObject = genericRepository.findProductsBySearchTerm(GPU.class, 0, 20, "rtx 40");
        System.out.println(jsonObject);
    }
}
