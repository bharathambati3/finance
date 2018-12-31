import React from "react";
import classes from "./MFFilter.css";
import DropDown from "../selectors/DropDown";
import {Button} from "@material-ui/core";
import DatePicker from "../../../UI/pickers/DatePicker";

const MFFilter = (props) => {

    let updateCompanyId = (id) => {
        let datum = constructData({companyId: id});
        props.updateData(datum);
    }

    let updateFromDate = (date) => {
        props.updateData(constructData({dateTimeRange :{startDate: formatDate(date)}}));
    }

    let formatDate = (date) => {
        if (date === "") {
            return null;
        } else {
            return date+"T00:00"; //server requires time value also.
        }
    }

    let updateToDate = (date) => {
        props.updateData(constructData({dateTimeRange :{endDate: formatDate(date)}}));
    }

    let copyData = (data, cData) => {
        Object.keys(data).forEach(key => {
            if (typeof data[key] === "object" && data[key] !== null) {
                if(cData[key] === undefined) {
                    cData[key] = {};
                }
                copyData(data[key], cData[key]);
            } else {
                cData[key] = data[key];
            }
        });
    };

    let constructData = (datum) => {
        let cData = props.request;
        copyData(datum, cData);
        return cData;
    };

    let companyId = props.request.companyId;
    let startDate = (props.request.dateTimeRange === undefined) ? "none":props.request.dateTimeRange.startDate;
    startDate = (startDate !== undefined && startDate !== null)?startDate.replace("T00:00", ""):"none";

    let endDate = (props.request.dateTimeRange === undefined) ? "none":props.request.dateTimeRange.endDate;
    endDate = (endDate !== undefined && endDate !== null)?endDate.replace("T00:00", ""):"none";

    return (
        <div className={classes.main}>
            <DropDown label="Company" data={props.companies} selected={companyId} updateSelected={updateCompanyId}/>
            <DatePicker label="start date" changeHandler={updateFromDate} defaultValue={startDate}/>
            <DatePicker label="end date" changeHandler={updateToDate} defaultValue={endDate}/>
            {/*<Button variant="contained" color="primary" className={classes.btn} onClick={props.handleSubmit}> {(props.btnName)? props.btnName: "Submit"} </Button>*/}
        </div>
    );
};

export default MFFilter;

