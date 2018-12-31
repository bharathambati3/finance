import React, {Component} from "react";
import axios from "axios";
import Summary from "./summary";
import Aux from "../../../hoc/aux";
import DropDown from "../selectors/DropDown";
import * as Config from '../../../../config/Config'

class Sips extends Component {

    constructor() {
        super();
        this.state = {
            investments: [],
            request: {
                sipDate: 0
            },
            initialized: false
        }
    }


    render() {

        let dContent = [
            {id: 1, field: "id", name: "Id", isKey: true, summary: "0"},
            {id: 2, field: "name", name: "name", summary: "-"},
            {id: 6, field: "sipDate", name: "sipDate", summary: "-"},
            {id: 3, field: "startDate", name: "startDate", summary: "-"},
            {id: 5, field: "endDate", name: "endDate", summary: "-"},
            {id: 4, field: "amount", name: "amount", summary: {operation: "sum", params: {fixed: 0}}},
        ];

        let sipDates = [
            {id: 0, name: "All"},
            {id: 10, name: "10"},
            {id: 12, name: "12"},
            {id: 14, name: "14"},
            {id: 15, name: "15"},
            {id: 16, name: "16"},
            {id: 18, name: "18"},
            {id: 21, name: "21"},
            {id: 25, name: "25"},
        ];

        let updateSipDate = (id) => {
            let idee = (id === 0)? null: id;
            let data = {userId: "a", sipDate: idee};
            axios.post(`${Config.ROOT_URL}${Config.GET_ALL_SIP_INFO}`, data)
                .then(response => {
                    let arr = response.data.infos.map(e => {
                        let data = {};
                        data.id = e.id;
                        data.name = e.schemeVo.name;
                        data.registeredOn = e.registeredOn;
                        data.startDate = e.startDate;
                        data.endDate = e.endDate;
                        data.amount = e.amount;
                        data.sipDate = e.sipDate;
                        return data;
                    });
                    this.setState({investments: arr, request: {sipDate: id}});
                });
        };

        if (!this.state.initialized) {
            updateSipDate(0);
            this.setState({initialized: true});
        }

        return (
            <Aux>
                <DropDown label="SipDate" data={sipDates} selected={this.state.request.sipDate} updateSelected={updateSipDate}/>
                <Summary data={this.state.investments} content={dContent}/>
            </Aux>
        );
    }
}


export default Sips;
