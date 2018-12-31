import React from 'react';
import SingleSip from './SingleSip'

const ListMonthlySip = (props) => {

  let listing = props.data.map((e, i) => <SingleSip clicked={props.clicked} data={e} key={i+"-"+e.scheme.id+"-"+e.sipDate}/>)

  return <table border="1" cellPadding="4">
    <tbody>
      <tr>
        <th>Scheme</th>
        <th>Sip Date</th>
        <th>Purchase Nav</th>
        <th>Invested On</th>
        <th>Amount</th>
        <th>Submit</th>
      </tr>
      {listing}
    </tbody>
  </table>
}

export default ListMonthlySip;