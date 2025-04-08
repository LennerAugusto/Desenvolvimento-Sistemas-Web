import * as FaIcons from 'react-icons/fa';
import * as AiIcons from 'react-icons/ai';

const SideBarData = ()=> [
    {
        page:'Pagina Principal',
        icon:<AiIcons.AiFillDashboard/>,
        path:'/dashboard'
    },
    {
        page:'Usuario',
        icon:<FaIcons.Fauser/>,
        path:'/usuario/listar'
    },
]

export default SideBarData;