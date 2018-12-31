import React from "react";
import classes from "./table.css";
import {BootstrapTable, TableHeaderColumn} from "react-bootstrap-table";

class Summary extends React.Component {

    constructor(props) {
        super(props);

        this.options = {
            onPageChange: this.onPageChange.bind(this),
            onSizePerPageList: this.sizePerPageListChange.bind(this)
        };
    }
    sizePerPageListChange(sizePerPage) {
        // console.log(`sizePerPage: ${sizePerPage}`);
    }

    onPageChange(page, sizePerPage) {
        // console.log(`page: ${page}, sizePerPage: ${sizePerPage}`);
    }

    render() {
        function priceFormatter(cell, row) {
            let className;
            if(row.earnings > 0) {
                className = classes["arrow-up"];
            } else if (row.earnings < 0) {
                className = classes["arrow-down"];
            }

            return `<div class=${classes.earnings}>${cell}</div> <i class=${className}></i>`;
        }

        function getFormatter(formatter) {
            if (formatter === "priceFormatter") {
                return priceFormatter;
            }
        }
        let columns = this.props.content.map(e => <TableHeaderColumn key={e.id} isKey={e.isKey} dataField={e.field} dataSort
                                                                dataFormat={ getFormatter(e.formatter) }> {e.name}</TableHeaderColumn>);
        let sColumns = this.props.content.map(e => <TableHeaderColumn key={e.id} isKey={e.isKey} dataField={e.field}/>);

        let datum = {};
        this.props.content.forEach(e => {
            if ((typeof e.summary) === "string") {
                datum[e.field] = e.summary;
            }
            if ((typeof e.summary) === "object") {
                let operation = e.summary.operation;
                if (operation === "sum") {
                    let params = e.summary.params;
                    let fixed = (params !== undefined && params.fixed !== undefined) ? params.fixed : 2;
                    datum[e.field] = this.props.data.map(d => d[e.field]).reduce((a, b) => a + b, 0).toFixed(fixed);
                } else if (operation === "min") {

                    datum[e.field] = this.props.data.map(d => {return d[e.field]} ).reduce((a, b) => (a < b)? a : b, "-");
                }
            }

        } );
        let sData = [
            datum
        ];

        return (
            <div>

                <BootstrapTable
                    data={this.props.data}
                    striped
                    hover
                    condensed
                    search
                    options={ this.options }
                >
                    {columns}
                </BootstrapTable>


                <BootstrapTable
                    data={sData}
                    striped
                    hover
                    condensed
                    version='4'
                >
                    {sColumns}
                </BootstrapTable>

            </div>
        )
    }
}

export default Summary;
