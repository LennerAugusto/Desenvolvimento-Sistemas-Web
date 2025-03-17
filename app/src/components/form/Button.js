import React, { Fragment } from 'react'

const Button = ({
  id,
  type,
  title,
  variant,
  cssClass,
  label,
  icon,
  onClick, 
  disabled=false 
}) => {
  return (
    <Fragment>
      <button
        id={id}
        type={type}
        title={title}
        className={`btn btn-${variant} ${cssClass}`}
        onClick={ type !== "submit" ? onClick: undefined }
        disabled={disabled}
      >
       {
        icon && (
          <i>{icon}</i>
        )
       } 
       &nbsp;&nbsp;{label}      
      </button>
    </Fragment>
  )
}

export default Button;

