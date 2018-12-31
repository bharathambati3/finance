import React, {Component} from "react";
import Sidebar from "../sidebar.js"
import {Route} from 'react-router-dom';
import Dashboard from "../../../container/Dashboard/dashboard.js"
import MutualFunds from "../../../container/investments/MutualFunds/mutualfunds";
import Content from "../../content/content";
import PPF from '../../investments/ppf/ppf';
import PF from '../../investments/pf/pf';
import NSC from '../../investments/nsc/nsc';
import Summary from '../../summary/summary';
import classes from './container.css';
import Demo from "../demo";

class Container extends Component {

    render() {

        this.props = {
            sideBarItems: [
                {key: 1, component: <Dashboard/> , name: "Dashboard", url: "/dash"},
                {key: 2, component: <MutualFunds/> , name: "Mutual Funds", url: "/mf"},
                {key: 3, component: <PPF/> , name: "PPF", url: "/ppf"},
                {key: 4, component: <PF/> , name: "PF", url: "/pf"},
                {key: 5, component: <NSC/> , name: "NSC", url: "/nsc"},
                {key: 6, component: <Summary/> , name: "Summary", url: "/summary"},
                {key: 7, component: <Demo/> , name: "Test", url: "/test"},
            ]
        };

        let routes = this.props.sideBarItems.map(e =>
            <Route
                key={e.key}
                path={e.url}
                exact={e.exact}
                render={() => <Content> {e.component} </Content>}/>);
        return (
            <div className={classes.main}>
                <Sidebar items={this.props.sideBarItems}/>
                {routes}
            </div>
        );
    }
}

export default Container;