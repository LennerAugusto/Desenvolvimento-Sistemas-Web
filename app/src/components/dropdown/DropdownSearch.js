
import React, { useCallback, useEffect, useRef, useState } from 'react';
import './dropdownsearch.css';

const DropdownSearch = ({
  label,
  options, //lista de cidade, lista de professor, lista disciplina, etc
  search, //nomeCidade, nomeProfessor, nomeDisciplina, etc 
  id, // idCidade, idProfessor, etc....
  selectedVal, // o valor selecionado, 
  handleChange, // a função que irá "pegar" para montar o objecto a ser salvo,  
  error, // mensagem de erro, por exemplo quando estiver em branco. 
  readonly=false, 
  disabled=false,
}) => {
  
  const [touched, setTouched] = useState(false);
  const [query, setQuery] = useState("");
  const [isOpen, setIsOpen ] = useState(false);
  const inputRef = useRef(null);

  const toggle = useCallback((e) => {
    if (query.trim() !== ""){
      setIsOpen(e && e.target === inputRef.current)
    } else {
      setIsOpen(false)
    }
  },[query]);

  useEffect(() =>{
    const handleClickOutside = (e) => {
      if (inputRef.current && !inputRef.current.contains(e.target)){
         setIsOpen(false);
      }
    } 
    document.addEventListener("click", handleClickOutside);
    return () => document.removeEventListener("click", handleClickOutside);
  }, [toggle]);

  useEffect(()=>{
     setQuery(selectedVal || '');
  },[selectedVal]);

  const getByDisplayValue = () => {
      if (query) return query;
      if (selectedVal) return selectedVal;
      return "";
  }

  const selectOption = (option) => {
    setQuery(option[search]);
    handleChange(option, option[search]);
    setIsOpen(false);
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

  const filter = (options) => {
    return options.filter(
      (option) => option[search].toLowerCase().indexOf(query.toLowerCase())
    );
  };

  return (

    <div className='dropdown'>
      <div className='control'>
        <div className='selected-value'>
          <label className="control-label app-label mb-1 ">{label}</label>
          <input 
             ref={inputRef}
             type="text"
             value={getByDisplayValue()}
             name="searchTermo"
             onChange={(e) => {
               setQuery(e.target.value);
               handleChange(null, '');
               if (e.target.value.trim() !== ''){
                setIsOpen(true);
               }else{
                setIsOpen(false);
               }
             }}
             onClick={toggle}
             className={getInputClass()}
             readOnly={readonly}
             disabled={disabled}
          />
        </div>
        <div className={`arrow ${ isOpen ? "open":"" }`}></div>
      </div>

      {/* <div className={` options ${ isOpen ? "open":"" } `}> */}
         {
          isOpen && filter(options).length > 0 ? (
            <div className="options open">
              { filter(options).map((option, index) => {
                   return (
                  <div onClick={()=> selectOption(option)} 
                      className={` option ${
                        option[search] === selectedVal ? "selected":""
                      }`}
                      key={`${id}-${index}`}
                  >
                    { option[search] }
                  </div>  
                )
              })}    
            </div>
          ) : (null)}
    </div>
  );
}


export default DropdownSearch
