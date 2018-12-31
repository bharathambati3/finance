import React, { Component } from 'react'
import axios from 'axios'
import Summary from './summary'
import Aux from '../../../hoc/aux'
import MFFilter from '../filters/MFFilter'
import * as Config from '../../../../config/Config'

class Investment extends Component {
  constructor (props) {

    console.log('Investment constructor is called again :)')
    super(props)
  }

  componentDidMount () {
    this.updateData(this.props.data.request);
  }

  dContent = [
    {id: 1, field: 'id', name: 'Id', isKey: true, summary: '0'},
    {id: 2, field: 'name', name: 'name', summary: 'Total'},
    {id: 3, field: 'amount', name: 'amount', summary: {operation: 'sum', params: {fixed: 0}}},
    {id: 4, field: 'currentValue', name: 'currentValue', summary: {operation: 'sum', params: {fixed: 0}}},
    // {id: 5, field: 'investmentType', name: 'investmentType', summary: '-'},
    {id: 6, field: 'investedOn', name: 'investedOn', summary: '-'},
    {id: 7, field: 'investmentPeriod', name: 'investmentPeriod', summary: '-'},
    {id: 8, field: 'purchaseNav', name: 'purchaseNav', summary: '-'},
    {id: 9, field: 'currentNav', name: 'currentNav', summary: '-'},
    {id: 10, field: 'earnings', name: 'earnings', summary: {operation: 'sum'}, formatter: 'priceFormatter'},
  ]

  updateData = (data) => {
    axios.post(`${Config.ROOT_URL}${Config.GET_INVESTMENTS}`, data)
      .then(response => {
        this.props.updateInv(response.data, data)
      })
  }

  handleSubmit = () => {
    this.updateData(this.props.data.request)
  }

  render () {

    return (
      <Aux>
        <MFFilter handleSubmit={this.handleSubmit} companies={this.props.companies} request={this.props.data.request}
                  updateData={this.updateData}/>
        {(this.props.data.investments.length > 0) ?
          <Summary data={this.props.data.investments} content={this.dContent}/> : null}
      </Aux>
    )
  }
}

export default Investment