
import React, { Fragment, useState } from 'react'

const Select = ({
  id,
  label,
  value,
  handleChange,
  handleBlur,
  array,
  error,
  readonly = false,
  disabled = false,
}) => {

  const [touched, setTouched] = useState(false);
  

  const onChangeHandler = (e) => {
     if (!readonly && !disabled ) {
        handleChange(e.target.name, e.target.value); 
     }
  }

  const getInputClass = () => {
      if (touched){
         if (error) {
           return "form-control is-invalid app-label mb-2 "
         } else if ( error === false){
           return "form-control is-valid app-label mb-3 "
         }
      }
      return "form-control app-label mb-2"
  }

  return (
   <Fragment>
      <div className="form-group">
          <label htmlFor={id}
          className='control-label app-label'>
            {label}:  
          </label>
          <div className="input-group">
             <select
                 id={id}
                 name={id}
                 value={value}
                 onChange={onChangeHandler}
                 className={getInputClass()}
             >
             <option value="" disabled>
                 { `Selecione o ${label}` }
              </option>   
              {
                array.map((arr, index) => (
                  <option key={index} value={arr.value}>{arr.name}</option>
                ))
              }
             </select>
          </div>
      </div>
   </Fragment>
  )
}

export default Select
