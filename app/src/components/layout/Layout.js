
import React, { Fragment, useState } from 'react';
import Header from './header/Header';
import SideBar from './menu/SideBar';
import './layout.css';


const Layout = ({ children }) => {

  const [toggle, setToggle ] = useState(false);

  const toggleClick = () => {
    setToggle(!toggle);
  }

  return (
    <Fragment>
      <Header toggleHeader={toggleClick} />
      <SideBar toggle_sidebar={toggle}/> 
      <main className={ toggle ? 'app-content active':'app-content' }>
         { children }
      </main>
    </Fragment>
  )
}

export default Layout;
