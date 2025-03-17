import React, { Fragment } from 'react'
import LinkButton from '../form/LinkButton'
import { BUTTON_SIZE, DANGER, INFO, SECONDARY } from '../../config/Config'
import * as FaIcons from 'react-icons/fa';


const RowActions = ({ path, id }) => {
  return (
     <Fragment>
        <td style={{ textAlign: "center" }}>
          <LinkButton
               to={`/${path}/alterar/${id}`}
               type="button"
               title={`Alterar os dados - ${path}`}
               variant={INFO}
               cssClass='btn-sm'
               icon={<FaIcons.FaPencilAlt size={BUTTON_SIZE}/>}
          />
          <LinkButton
               to={`/${path}/excluir/${id}`}
               type="button"
               title={`Excluir os dados - ${path}`}
               variant={DANGER}
               cssClass='btn-sm'
               icon={<FaIcons.FaTrashAlt size={BUTTON_SIZE}/>}
          />
           <LinkButton
               to={`/${path}/consultar/${id}`}
               type="button"
               title={`Consultar os dados - ${path}`}
               variant={SECONDARY}
               cssClass='btn-sm'
               icon={<FaIcons.FaSearchPlus size={BUTTON_SIZE}/>}
          />

        </td>
     </Fragment>
  )
}

export default RowActions

