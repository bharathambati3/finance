import React, { Component } from 'react'
import './dashboard.css'
import { connect } from 'react-redux'
import { bindActionCreators } from 'redux'
import SchemeCharts from '../../components/dashboard/Charts/SchemeCharts'
import DropDownInput from '../../components/UI/Input/DropDownInput'
import { getInvestments, onCompanySelected, onSchemeSelected } from '../../actions/Dashboard/DashboardActions'
import {Button} from "@material-ui/core";
import moment from 'moment'

class Dashboard extends Component {

  extractInvestmentDataInfo = () => {
    let outerResp = this.props.investmentsData;
    if (outerResp
      && outerResp.response
      && outerResp.response.status !== 200) {
      return outerResp.message;
    }
    if (outerResp && outerResp.data) {
      let chartData = outerResp.data.map(e => { return {"label": moment(e.purchasedOn).format('YYYY-MM-DD'), "value": e.purchaseNav}})
      return <SchemeCharts data={chartData} name={this.getSchemeName()}/>
    }
    return null;
  }

  getSchemeName = () => {
    let schemeObj = this.props.schemes[this.props.selectedScheme - 1];
    if (schemeObj) {
      return schemeObj.name;
    }
    return "";
  }

  componentDidMount () {
    this.props.getInvestments(this.props.selectedScheme)
  }

  schemeChanged = (e) => {
    this.props.onSchemeSelected(e);
    this.props.getInvestments(this.props.selectedScheme)
  }

  render () {
    let charts = this.extractInvestmentDataInfo();
    return (
      <div>
        <DropDownInput placeholder="Schemes"
                       value={this.props.selectedScheme}
                       handleChange={this.schemeChanged}
                       data={this.props.schemes}
        />
        {charts}
      </div>
    )
  }
}

const mapStateToProps = state => {
  return {
    companies: state.common.companies,
    schemes: state.common.schemes,
    selectedCompany: state.dashboard.selectedCompany,
    selectedScheme: state.dashboard.selectedScheme,
    investmentsData: state.dashboard.investmentsData,
  }
}

const mapDispatchToProps = dispatch => {
  return bindActionCreators({
    onCompanySelected,
    onSchemeSelected,
    getInvestments
  }, dispatch)
}

export default connect(mapStateToProps, mapDispatchToProps)(Dashboard)
