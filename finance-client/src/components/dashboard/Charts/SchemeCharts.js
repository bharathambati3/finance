import React, { Component } from 'react'
import FusionCharts from 'fusioncharts'
import Charts from 'fusioncharts/fusioncharts.charts'
import ReactFC from 'react-fusioncharts'
import DropDown from '../../investments/mf/selectors/DropDown'

Charts(FusionCharts);
const SchemeCharts = (props) => {

  let chartConfigs = {
    type: 'line', //column2d, column3d, world, spline, line
    width: 600,
    height: 300,
    dataFormat: 'json',
    dataSource: {
      'chart': {
        'caption': `Scheme performance ${props.name}`,
        'subCaption': 'Nav vs Time',
        'xAxisName': 'Time',
        'yAxisName': 'Nav',
        'numberSuffix': '',
        'theme': 'fusion',
      },
      'data': props.data
    }

  }

  return (
    <div>
      <ReactFC {...chartConfigs} />
    </div>
  )
}

export default SchemeCharts