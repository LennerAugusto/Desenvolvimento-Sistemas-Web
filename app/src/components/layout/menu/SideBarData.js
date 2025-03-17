
import * as AiIcon from 'react-icons/ai';
import * as FaIcon from 'react-icons/fa';

const SideBarData = [
   {
     page:'Página Principal',
     icon: <AiIcon.AiFillDashboard/>,
     path:'/dashboard',
   },
   {
    page:'Segurança',
    icon:<AiIcon.AiFillSecurityScan/>,
    sub_menu:[
      {
        page:'Usuário',
        icon:<FaIcon.FaUser />,
        path:'/usuario/listar',
      },
      {
        page:'Direito de Acesso',
        icon:<FaIcon.FaTable />,
        path:'/role/listar',
      }
    ]
   }

]


export default SideBarData;