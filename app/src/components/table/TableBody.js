import React from 'react'
import RowActions from './RowActions'

const TableBody = ({ headers, currentRecords, path }) => {
  
  const idField = headers.find(
    (header) => header.nome.toLowerCase() === "id"
  )?.field;
  
  return (
    <tbody>
         {
          currentRecords.length > 0 ? (
            currentRecords.map(( registro, index) =>(
                 <tr key={index} className='app-coluna-detalhe-centro app-label'>
                     {
                       headers.map((header, colIndex )=>(
                           header.print !== false && (
                             <td key={colIndex} data-label={header.nome}>
                              {
                                header.formatter ? header.formatter(registro[header.field], registro)
                                                 : registro[header.field]
                              }
                             </td>
                           )   
                       ))}
                       <RowActions 
                          path={path}
                          id={registro[idField]}
                       />
                 </tr>
            ))
          ):(
            <tr>
              <td colSpan={headers.length+1}>Nenhum Registro encontrado....</td>
            </tr>
          )
         } 
    </tbody>
  )
}

export default TableBody
