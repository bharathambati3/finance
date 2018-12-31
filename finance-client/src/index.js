import React from "react";
import ReactDOM from "react-dom";
import App from "./components/app/app.js";
import '../node_modules/react-bootstrap-table/dist/react-bootstrap-table-all.min.css';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css'
import CssBaseline from '@material-ui/core/CssBaseline';
import {Provider} from 'react-redux';
import promise from "redux-promise";
import {createStore, applyMiddleware} from 'redux';
import reducers from "./reducers";

const createStoreWithMiddleware = applyMiddleware(promise)(createStore);

ReactDOM.render(
    <Provider store={createStoreWithMiddleware(reducers)}>
        <React.Fragment>
            <CssBaseline />
            <App />
        </React.Fragment>
    </Provider>,
    document.getElementById("root")
);