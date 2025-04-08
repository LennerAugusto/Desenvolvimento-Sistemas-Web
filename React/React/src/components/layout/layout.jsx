import React, { Fragment } from "react";
import Headers from "../headers/headers";
import Sidebar from "../sidebar/sidebar";

const Layout = ({children}) => {
    return(
        <Fragment>
            <Headers/>
            <Sidebar/>
            <div className="app-content">
                {children}
            </div>
        </Fragment>
    )

}

export default Layout;