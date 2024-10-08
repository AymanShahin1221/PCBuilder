/**
 *
 * @param {Object} props - props object
 * @param {String} props.category - categoryName to be displayed on button
 */

function GenericAddPartButton(props : any) {

    function createPrefix(categoryName : string) {
        if(categoryName === "Memory" || categoryName === "Storage")
            return "Choose "

        else if(categoryName === "OS")
            return "Choose an "

        else
            return "Choose a "
    }

    return (
        <div className="BuildPage_add-part-button">
            <a href={"build/" + props.category + "#page=1"}>
                <button type="button" className="BuildPage_add-item-button"> + {createPrefix(props.category)} {props.category}</button>
            </a>
        </div>
    );
}

export default GenericAddPartButton;