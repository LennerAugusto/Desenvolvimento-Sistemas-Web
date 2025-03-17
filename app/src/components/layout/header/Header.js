
import React, { Fragment, useState } from 'react'
import './header.css';
import * as FaIcons from 'react-icons/fa';
import { DEFAULT_IMAGEM_THUMBNAIL } from '../../../config/Config';


const Header = ({ toggleHeader }) => {
  
  const [toggle, setToggle] = useState(true);

  const toggleClick1 = () => {
     setToggle(!toggle);
     toggleHeader(toggle);
  }


  return (
    <Fragment>
       <header className='app-header'>
          <div className="app-leftarea">
              <h4>
                SISTEMA<span>IFSP</span>
              </h4>
          </div>
          <div className='app-toggle'>
             <i>
               <FaIcons.FaBars onClick={() => toggleClick1()}/>
             </i>
          </div>
          <div className='app-profile'>
             <img src={DEFAULT_IMAGEM_THUMBNAIL} alt="foto"/>
             <span>Francisco</span> 
             <div className="app-logout">
                <i>
                  <FaIcons.FaSignOutAlt />
                </i>
             </div>
          </div>
          
       </header>
    
    
    </Fragment>
  )
}

export default Header;
