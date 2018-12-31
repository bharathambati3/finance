import React, { Component } from 'react'
import { connect } from 'react-redux'
import { bindActionCreators } from 'redux'
import {
  createNewInvestment,
  requestMonthlySipData, selectedMonth, showSipInfo,
  storeSipData
} from '../../../../actions/ManageMonthlySipAction/manageMontlySipAction'
import ListMonthlySip from '../../../../components/investments/mf/sub_components/Manage/ListMonthlySip/ListMonthlySip';
import { SUBMITTED_MODE } from '../../../../components/investments/mf/sub_components/Manage/ListMonthlySip/SingleSip'
import SimpleModal from '../../../../components/UI/modal/SimpleModal'
import MonthlySipFilter from '../../../../components/investments/mf/filters/MonthlySipFilter/MontlySipFilter'

class ManageMonthlySip extends Component {

  componentDidMount() {
    this.getMonthlySipInfo();
  }

  render () {

    let listing = (this.props.searchData) ? <ListMonthlySip clicked={this.singleInvestmentClick} data={this.props.searchData}/> : null
    let sipInfo = (this.props.sipInfo) ? <SimpleModal info={this.props.sipInfo} handleClose={() => this.props.showSipInfo(null)}/>: null;
    return <div>
      {sipInfo}
      <MonthlySipFilter val={this.props.filterMonth}
                        onChange={(e) => this.props.selectedMonth(e.target.value)}
                        getMonthlySipInfo={this.getMonthlySipInfo}/>
      {listing}
    </div>
  }

  getMonthlySipInfo = () => {
    let req = {
      month: this.props.filterMonth
    }
    let success = (data) => {
      console.log(data.data);
      this.props.storeSipData(data.data);
    }

    let failure = (error) => {
      console.log(error)
    }

    this.props.requestMonthlySipData(req, success, failure)
  }

  singleInvestmentClick = (data) => {
    console.log(data);
    if (data.mode === SUBMITTED_MODE) {
      this.props.showSipInfo(data);
    } else {

      let success = () => {
        this.getMonthlySipInfo();
      }

      let failure = (error) => {
        console.log(error);
      }

      this.props.createNewInvestment(data, success, failure);
    }
  }
}

const mapStateToProps = state => {
  return {
    searchData: state.monthlySipData.searchData,
    sipInfo: state.monthlySipData.sipInfo,
    filterMonth: state.monthlySipData.filterMonth,
  }
}

const mapDispatchToProps = dispatch => {
  return bindActionCreators({
    requestMonthlySipData,
    storeSipData,
    showSipInfo,
    selectedMonth,
    createNewInvestment,
  }, dispatch)
}

export default connect(mapStateToProps, mapDispatchToProps)(ManageMonthlySip)