import React, { Fragment } from "react";
import SideBarData from "./sidebardata";

const Sidebar = () => {
    return(
        <Fragment>
            <div className="app-sidebar">
         <ul>
            {
                SideBarData.map((item,index) => (
                        <ShowItem
                            path={item.path}
                            icon={item.icon}
                            page={item.page}
                            index={index}
                        />
                ))
            }
         </ul>
            </div>
        </Fragment>
    )
}

export default Sidebar;