import React from "react";
import {NavLink} from "react-router-dom";
import {withStyles} from "@material-ui/core/styles";
import List from "@material-ui/core/List";
import ListItem from "@material-ui/core/ListItem";
import ListItemText from "@material-ui/core/ListItemText";


const styles = theme => ({
    root: {
        width: '175px',
        maxWidth: '360px',
        backgroundColor: '#ced9ff',
        borderRight: "1px solid white"
    },
});

function Sidebar(props) {

    const { classes } = props;
    let elems = props.items.map(e =>
        <NavLink key={e.key} activeClassName={classes.active} to={e.url}>
            <ListItem button>
                <ListItemText primary={e.name} />
            </ListItem>
        </NavLink> );

    return(

        <div className={classes.root}>
            <List component="nav">
                {elems}
            </List>
        </div>
    );
}

export default withStyles(styles)(Sidebar);