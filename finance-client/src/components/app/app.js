import React, {Component} from 'react'
import classes from './App.css'
import Main from '../main/main.js'
import { BrowserRouter } from 'react-router-dom'
import { connect } from 'react-redux'
import { bindActionCreators } from 'redux'
import { loadCompanyInfo, loadSchemeInfo } from '../../actions/Common/CommonActions'

class App extends Component {

  componentDidMount = () => {
    this.props.loadCompanyInfo();
    this.props.loadSchemeInfo();
  }

  render () {
    return (
      <BrowserRouter>
        <div className={classes.App}>
          <Main/>
        </div>
      </BrowserRouter>
    )
  }
}

const mapDispatchToProps = dispatch => {
  return bindActionCreators({
    loadCompanyInfo,
    loadSchemeInfo,
  }, dispatch);
};

export default connect(null, mapDispatchToProps)(App)