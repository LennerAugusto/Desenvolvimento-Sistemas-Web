import React, { Fragment } from "react";
import * as Faicons from 'react-icons/fa';
import { USER_PHOTO } from "../../config/config";

const Headers = () => {
    return (
        <Fragment>
            <header className="app-header">
                <div className="app-leftarea">
                    <span>
                        Sistema de Controle
                    </span>
                </div>
                <div className="app-toggle">
                    <i><Faicons.FaBars/></i>
                </div>
                <div className="app-profile">
                    <img src={USER_PHOTO} alt="foto do perfil"/>
                    <span>Cocão</span>
                    <div className="app-logout">
                        <i><Faicons.FaSignInAlt/></i>
                    </div>
                </div>
            </header>
        </Fragment>
    )
}

export default Headers;