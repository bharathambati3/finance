import React from 'react'
import PropTypes from 'prop-types'
import { withStyles } from '@material-ui/core/styles'
import Tabs from '@material-ui/core/Tabs'
import Tab from '@material-ui/core/Tab'
import { withRouter } from 'react-router-dom'

const styles = theme => ({
  root: {
    flexGrow: 1,
    backgroundColor: '#ced9ff',
    '& button': {
      marginRight: '0px'
    }
  },
  tabsRoot: {
    borderBottom: '1px solid #e8e8e8',
  },
  tabsIndicator: {
    backgroundColor: '#1890ff',
  },
  tabRoot: {
    borderRadius: '5px',
    textTransform: 'initial',
    fontWeight: theme.typography.fontWeightRegular,
    marginRight: theme.spacing.unit * 4,
    fontFamily: [
      '-apple-system',
      'BlinkMacSystemFont',
      '"Segoe UI"',
      'Roboto',
      '"Helvetica Neue"',
      'Arial',
      'sans-serif',
      '"Apple Color Emoji"',
      '"Segoe UI Emoji"',
      '"Segoe UI Symbol"',
    ].join(','),
    '&:hover': {
      backgroundColor: '#6057ff',
      color: '#40a9ff',
      opacity: 1,
    },
    '&$tabSelected': {
      backgroundColor: '#ced9ff',
      color: '#1890ff',
      fontWeight: theme.typography.fontWeightMedium,
    },
    '&:focus': {
      color: '#40a9ff',
      outline: 0
    }
  },
  tabSelected: {},
  typography: {
    padding: theme.spacing.unit * 1,
  }
})

class CustomizedTabs extends React.Component {

  constructor (props) {
    super()
    let initialSelection = this.selectItem(props, props.items)
    this.state = {
      value: initialSelection,
    }

  }

  selectItem = (props, mi) => {
    let relUrl = props.history.location.pathname

    for (let index = 0; index < mi.length; index++) {

      if (mi[index].exact) {
        if (relUrl === mi[index].url) {
          return mi[index].key
        }
      } else {
        if (relUrl.startsWith(mi[index].url)) {
          return mi[index].key
        }
      }
    }
    return 0
  }

  render () {
    const {classes} = this.props
    const {value} = this.state

    let self = this
    let handleChange = (event, value) => {
      self.setState({value})

      let url = self.props.items.filter(i => i.key === value).map(i => i.url)[0]
      self.props.history.replace(url)
    }

    let list = this.props.items.map(i =>

      <Tab
        key={i.key}
        disableRipple
        classes={{root: classes.tabRoot, selected: classes.tabSelected}}
        label={i.name}
      />)

    return (
      <div className={classes.root}>
        <Tabs
          value={value}
          onChange={handleChange}
          classes={{root: classes.tabsRoot, indicator: classes.tabsIndicator}}
        >
          {list}
        </Tabs>
      </div>
    )
  }
}

CustomizedTabs
  .propTypes = {
  classes: PropTypes.object.isRequired,
}

export
default

withStyles(styles)

(
  withRouter(CustomizedTabs)
)