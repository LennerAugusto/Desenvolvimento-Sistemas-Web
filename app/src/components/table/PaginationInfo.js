
import React from 'react'

const PaginationInfo = ({
  page,
  pageSize,
  totalElements
}) => {

  const totalpages = Math.ceil(totalElements/pageSize);

  return (
    <p className='app-label-pagination'>
      Mostrando&nbsp;&nbsp;
      <span className='badge bg-secondary'>
        { pageSize * ( page - 1) + 1 }
      </span>
      &nbsp;até &nbsp;
      <span className='badge bg-secondary'>
        { Math.min(pageSize * page, totalElements) }
      </span>
      &nbsp;de&nbsp;
      <span className='badge bg-secondary'>
        { totalpages }
      </span>
      &nbsp;Páginas de&nbsp;
      <span className='badge bg-secondary'>
        { totalElements }
      </span>
      &nbsp;Registros cadastrados
    </p>
  )
}

export default PaginationInfo
