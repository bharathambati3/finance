import React from "react";
import Header from "../header/header.js";
import Footer from "../footer/footer.js";
import Container from "../sidebar/container/container.js";
import classes from "./main.css";


const Main = () => {

    return (
        <div className={classes.main}>
            <Header/>
            <Container/>
            <Footer/>
        </div>
    )
};

export default Main;