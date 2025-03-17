
import React, { Fragment } from 'react'

const MensagemErro = ({ error, mensagens }) => {
  return (
    <Fragment>
      {
          error && (
            <div className='invalid-feedback'>
              {
                mensagens.map((erro, index)=>(
                  <p key={index} style={{ margin:"0", color:"red"}}>
                     <span>{erro}</span>
                  </p>
                ))
              }

            </div>
          )
      }
    </Fragment>
  )
}

export default MensagemErro
