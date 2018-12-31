import React from 'react'
import classes from './SipContainer.css'
import Sips from '../sips'
import UpComingSips from '../upcomingsips'
import ManageMonthlySip from '../../../../../container/investments/MutualFunds/ManageMonthlySip/ManageMontlySip'
import MainHeader from '../../header'
import { Redirect, Route } from 'react-router-dom'
import { Switch } from 'react-router'
import SipDailyAmount from '../SipDailyAmount'

const SipContainer = () => {

  let mainItems = [
    {key: 0, component: <Sips/>, name: 'Reg Sips', url: '/mf/sips/show', exact: true},
    {key: 1, component: <UpComingSips/>, name: 'UpComingSips', url: '/mf/sips/upcomingsips', exact: true},
    {key: 2, component: <SipDailyAmount/>, name: 'Daily amount', url: '/mf/sips/dailyAmount', exact: true},
    {key: 3, component: <ManageMonthlySip/>, name: 'Manage', url: '/mf/sips/manage', exact: true},
  ]

  let routes = mainItems.map(e =>
    <Route
      key={e.key}
      path={e.url}
      exact={e.exact}
      render={() => e.component}
    />)

  return (
    <div>
      <MainHeader items={mainItems}/>
      <Switch>
        {routes}
        <Redirect from="/mf/sips" exact to="/mf/sips/show" />
      </Switch>
    </div>
  )
}

export default SipContainer
