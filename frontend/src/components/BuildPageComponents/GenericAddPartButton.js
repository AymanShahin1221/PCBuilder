/**
 *
 * @param {Object} props - props object
 * @param {String} props.category - categoryName to be displayed on button
 */

function GenericAddPartButton(props) {

    function createPrefix(categoryName) {
        if(categoryName === "Memory" || categoryName === "Storage")
            return "Choose "

        else if(categoryName === "OS")
            return "Choose an "

        else
            return "Choose a "
    }

    return (
        <div className="BuildPage_add-part-button">
            <a href="#">
                <button type="button" className="BuildPage_add-item-button"> + {createPrefix(props.category)} {props.category}</button>
            </a>
        </div>
    );
}

export default GenericAddPartButton;