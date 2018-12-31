import React, {Component} from "react";
import SearchBox from '../../filters/SearchBox';
import DatePicker from "../../../../UI/pickers/DatePicker";
import classes from "./Manage.css";
import TextField from "@material-ui/core/es/TextField/TextField";
import FormControl from "@material-ui/core/es/FormControl/FormControl";
import InputLabel from "@material-ui/core/es/InputLabel/InputLabel";
import Select from "@material-ui/core/es/Select/Select";
import MenuItem from "@material-ui/core/es/MenuItem/MenuItem";
import Button from "@material-ui/core/es/Button/Button";
import axios from 'axios';
import * as Config from '../../../../../config/Config'

class Manage extends Component {

    constructor(props) {
        super(props);
        this.state = {
            schemes: [],
            schemeInput: "",
            schemesLoaded: false,
            selectedSchemeId: null,
            selectedScheme: false,
            type: "",
            purchasedOn: null,
            investedOn: null,
            purchaseNav: null,
            amount: null,
        }
    }

    render() {

        let schemeNames = this.state.schemes.map(s => s.name);
        let loadSchemeDataHandler = (schemes) => {
            this.setState({schemes: schemes, schemesLoaded: true});
        };

        let changeHandler = (e) => {
            let name = e.target.value;
            this.setState({schemeInput: name, selectedScheme: false});
        };

        let onSelectedHandler = (selected) => {
            let id = this.state.schemes.filter(d => d.name === selected)[0].id;
            this.setState({selectedSchemeId: id, schemeInput: selected, selectedScheme: true});
        };

        let onPurchaseDateHandler = (date) => {
            this.setState({purchasedOn: date + "T00:00"})
        };

        let onInvestedDateHandler = (date) => {
            this.setState({investedOn: date + "T00:00"})
        };

        let onPurchaseNavUpdate = (e) => {
            this.setState({purchaseNav: e.target.value});
        };

        let onAmountUpdate = (e) => {
            this.setState({amount: e.target.value});
        };

        let typeChangeHandler = (e) => {
            this.setState({type: e.target.value});
        };

        let onSubmitHandler = () => {
            let data = {
                userId: "a",
                schemeId: this.state.selectedSchemeId,
                purchasedOn: this.state.purchasedOn,
                investedOn: this.state.investedOn,
                amount: this.state.amount,
                purchaseNav: this.state.purchaseNav,
                type: (this.state.type === "") ? null : this.state.type,
            };

            axios.post(`${Config.ROOT_URL}${Config.CREATE_INVESTMENT}`, data)
                .then(response => {
                    this.setState({
                        schemeInput: "",
                        type: "",
                        purchasedOn: null,
                        investedOn: null,
                        purchaseNav: null,
                        amount: null,
                    });
                }).catch(e => {
                    console.log(e.response.data.message);
            })
        };

        return (
            <div className={classes.root}>
                <SearchBox
                    label="Scheme"
                    url={`${Config.ROOT_URL}${Config.GET_ALL_SCHEMES}`}
                    loadData={!this.state.schemesLoaded}
                    data={schemeNames}
                    filter={this.state.schemeInput}
                    change={changeHandler}
                    selected={onSelectedHandler}
                    choosen={this.state.selectedScheme}
                    updateData={loadSchemeDataHandler}/>

                <DatePicker
                    label="Purchased on"
                    changeHandler={onPurchaseDateHandler}
                    defaultValue={null}/>

                <DatePicker label="Invested on"
                            changeHandler={onInvestedDateHandler}
                            defaultValue={null}/>
                <TextField
                    required
                    label="amount"
                    margin="normal"
                    onChange={onAmountUpdate}
                />
                <TextField
                    required
                    label="purchase nav"
                    margin="normal"
                    onChange={onPurchaseNavUpdate}
                />
                <FormControl className={classes.formControl}>
                    <InputLabel htmlFor="age-simple">Type  </InputLabel>
                    <Select
                        value={this.state.type}
                        onChange={typeChangeHandler}
                    >
                        <MenuItem value={null}>
                            <em>None</em>
                        </MenuItem>
                        <MenuItem value={"SIP"}>{"SIP"}</MenuItem>
                        <MenuItem value={"LUMPSUM"}>{"LUMPSUM"}</MenuItem>
                    </Select>
                </FormControl>

                <Button onClick={onSubmitHandler} variant="contained" color="primary" className={classes.button}>
                    Primary
                </Button>
            </div>
        )
    }
}


export default Manage;