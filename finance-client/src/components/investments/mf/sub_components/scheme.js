import React, { Component } from 'react'
import axios from 'axios'
import Summary from './summary'
import Aux from '../../../hoc/aux'
import MFFilter from '../filters/MFFilter'
import * as Config from '../../../../config/Config'

class Scheme extends Component {
  constructor () {
    super()
  }

  componentDidMount () {
    this.updateData(this.props.data.request);
  }

  dContent = [
    {id: 1, field: 'id', name: 'Id', isKey: true, summary: '0'},
    {id: 2, field: 'name', name: 'name', summary: 'Total'},
    {id: 3, field: 'amount', name: 'amount', summary: {operation: 'sum', params: {fixed: 0}}},
    {id: 4, field: 'currentValue', name: 'currentValue', summary: {operation: 'sum', params: {fixed: 0}}},
    {id: 5, field: 'latestNavUpdate', name: 'latestNavUpdate', summary: ''},
    {id: 6, field: 'earnings', name: 'earnings', summary: {operation: 'sum'}, formatter: 'priceFormatter'},
  ]

  updateData = (data) => {

    axios.post(`${Config.ROOT_URL}${Config.SCHEME_WISE_INVESTMENT}`, data)
      .then(response => {
        this.props.update(response.data, data)
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
        {/*<MFFilter data={this.state.data} handleSubmit={handleSubmit} updateData={updateData}/>*/}
        {(this.props.data.investments.length > 0) ?
          <Summary data={this.props.data.investments} content={this.dContent}/> : null}
      </Aux>

    )
  }
}

export default Scheme