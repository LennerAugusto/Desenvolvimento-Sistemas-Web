
import React, { Fragment } from 'react'
import Navegacao from '../components/mensagem/Navegacao';
import * as AiIcons from 'react-icons/ai';
import * as MdIcons from 'react-icons/md';
import { BUTTON_SIZE_SHOW_MESSAGE } from '../config/Config';


const Dashboard = () => {
  return (
    <Fragment>
      <Navegacao 
         titulo="Página Principal"
         iconTitulo={< AiIcons.AiFillDashboard size={BUTTON_SIZE_SHOW_MESSAGE}/>}
         descricao="Página principal do Sistema"
         iconReturn={<MdIcons.MdList size={BUTTON_SIZE_SHOW_MESSAGE}/>}
         url="/dashboard"
         tituloUrl="Página Principal"
      
      />
    </Fragment> 
  )
}

export default Dashboard;
