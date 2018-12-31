import React from "react";
import classes from "./content.css";


const content = (props) => {
    return (
        <div className={classes.main}>
            {props.children}
        </div>
    );
};

export default content;