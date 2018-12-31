import React, { Component } from 'react'
import Company from '../../../components/investments/mf/sub_components/Company'
import Investment from '../../../components/investments/mf/sub_components/investment'
import Scheme from '../../../components/investments/mf/sub_components/scheme'
import { Route } from 'react-router-dom'
import MainHeader from '../../../components/investments/mf/header'
import axios from 'axios'
import * as Config from '../../../config/Config'
import SipContainer from '../../../components/investments/mf/sub_components/SipContainer/SipContainer'
import { Switch } from 'react-router'

class MutualFunds extends Component {

  constructor () {
    super()
    this.state = {
      companies: [],
      investment: {
        investments: [],
        request: {
          userId: 'a',
          companyId: 0,
        }
      },
      scheme: {
        investments: [],
        request: {
          userId: 'a',
          companyId: 0,
        }
      },
      company: {
        investments: [],
        request: {
          userId: 'a',
          companyId: 0,
        }
      }
    }
  }

  componentDidMount () {
    axios.get(`${Config.ROOT_URL}${Config.GET_ALL_COMPANIES}`)
      .then(response => {
        let companies = response.data
        let none = {id: this.state.investment.request.companyId, name: 'All'}
        companies.push(none)
        this.setState({companies: companies})
      })
  }

  updateInvestment = (updatedInvestments, req) => {

    let inv = {...this.state.investment}
    inv.investments = updatedInvestments
    inv.request = req
    this.setState({investment: inv})
  }

  updateScheme = (updatedInvestments, req) => {
    let inv = {...this.state.scheme}
    inv.investments = updatedInvestments.investments
    inv.request = req
    this.setState({scheme: inv})
  }

  updateCompany = (updatedInvestments, req) => {
    let inv = {...this.state.company}
    inv.investments = updatedInvestments.investments
    inv.request = req
    this.setState({company: inv})
  }

  mainItems = () => {
    return [
      {
        key: 0,
        component: <Company companies={this.state.companies} data={this.state.company} update={this.updateCompany}/>,
        name: 'Company',
        url: '/mf',
        selected: false,
        exact: true
      },
      {
        key: 1,
        component: <Scheme companies={this.state.companies} data={this.state.scheme} update={this.updateScheme}/>,
        name: 'Scheme',
        url: '/mf/scheme',
        selected: false,
        exact: true
      },
      {
        key: 2,
        component: <Investment companies={this.state.companies} data={this.state.investment}
                               updateInv={this.updateInvestment}/>,
        name: 'Investments',
        url: '/mf/investment',
        selected: true,
        exact: true
      },
      {key: 3, component: <SipContainer/>, name: 'Sips', url: '/mf/sips', selected: true, exact: false},
    ]
  }

  routes = () => {
    return this.mainItems().map(e =>
      <Route
        key={e.key}
        path={e.url}
        exact={e.exact}
        render={() => e.component}
      />)
  }

  render () {

    return (
      <div>
        <MainHeader items={this.mainItems()}/>
        <Switch>
          {this.routes()}
        </Switch>
      </div>
    )
  }
}

export default MutualFunds;