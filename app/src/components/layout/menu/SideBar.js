import React, { Fragment } from 'react'
import items from './SideBarData';

import './sidebar.css';
import ShowItens from './ShowItens';


const SideBar = ({ toggle_sidebar }) => {
  return (
    <Fragment>
        <div className={ toggle_sidebar ? 'app-sidebar active' : 'app-sidebar '}>
           {
              items.map((item, index)=>{
                return(
                  <ShowItens 
                     key={index}
                     item={item}
                  />
                )
              })
           } 
        </div>
    </Fragment>
  )
}

export default SideBar
