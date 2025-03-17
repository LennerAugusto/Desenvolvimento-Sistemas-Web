import React, { Fragment } from 'react'

const TableHeader = ({
  headers,
  handleSort,
  getSortIcon
}) => {
  return (
    <Fragment>
      <thead className='cf'>
        <tr>
          {
            headers.map((header) => (
               header.print !== false && (
                <th key={header.field} 
                    onClick={() => header.sort && handleSort(header.field)}  
                    className='app-cabecalho-tabela p-3 mb-2 bg-success text-white '>
                    {header.nome} 
                    {header.sort ? getSortIcon(header.field) : '' }
                </th>
               )              
            ))
          }
          <th className='app-cabecalho-tabela p-3 mb-2 bg-success text-white'>Ações</th>

        </tr>
      </thead>
    </Fragment>
  )
}

export default TableHeader
