import React, { Fragment } from 'react'
import { Link } from 'react-router-dom'
import './navegacao.css';

const Navegacao = ({ titulo, 
                     iconTitulo, 
                     descricao, 
                     iconReturn,
                     caminho,
                     url,
                     tituloUrl }) => {
  return (
    <Fragment>
      <div className='app-titulo-sistema'>
        <div>
          <h3>
            <i>{ iconTitulo }</i>
            { titulo }
          </h3>
          <p>{descricao}</p>
        </div>
        <div className='font-navegacao'>
           <ul className='breadcrumb'>
              <li className='breadcrumb-item'>
                <i>{iconReturn}</i>
              </li>
              {
                 caminho ? 
                     <li className='breadcrumb-item'>{caminho}</li>
                     :null
              }
              <li className='breadcrumb-item'>
                <Link to={url}>{tituloUrl}</Link>
              </li>
           </ul>
        </div>

      </div>
    </Fragment>
  )
}

export default Navegacao
