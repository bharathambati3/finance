import React from "react";
import DropDownInput from "../../../UI/Input/DropDownInput";

let DropDown = (props) => {
    let onSelection = (event) => {
        props.updateSelected(event.target.value);
    };

    let data = {
        placeholder: props.label,
        name: "name",
        value: props.selected,

        data: props.data,
        handleChange: onSelection
    };

    return (
        <div>
            <DropDownInput {...data} />
        </div>
    );
}

export default DropDown;
