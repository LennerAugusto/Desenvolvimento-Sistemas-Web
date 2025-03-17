
import React, { Fragment, useMemo, useState } from 'react'
import Table from '../table/Table';
import TableHeader from '../table/TableHeader';
import TableBody from '../table/TableBody';
import LinkButton from '../form/LinkButton';
import { ARROW_DOWN, ARROW_UP, BUTTON_SIZE, PRIMARY } from '../../config/Config';
import * as FaIcons from 'react-icons/fa';
import './tabela.css';
import SearchBar from '../table/SearchBar';
import PaginationInfo from '../table/PaginationInfo';
import Pagination from '../table/Pagination';

const TabelaPaginadaCliente = ({ 
  headers, 
  data, 
  path 
}) => {

  const [sortConfig, setSortConfig]=useState({ key: null, direction: 'asc' })
  const [searchTerm, setSearchTerm]=useState('');
  const [currentPages, setCurrenPages] = useState(1);
  const [recordsPerPage, setRecordsPerPage] = useState(5);

  const filteredData = useMemo(() => {
     return data.filter( item =>
         Object.values(item)
               .some( value => String(value).toLowerCase()
               .includes(searchTerm.toLowerCase()))
     );
  },[data, searchTerm])

  const handleSort = (key) => {

    let direction = 'asc';
    if (sortConfig.key === key && sortConfig.direction === 'asc'){
      direction='desc';
    } else if (sortConfig.key === key && sortConfig.direction === 'desc'){
      direction=null;
    }
    setSortConfig({key, direction})

  } 

  const sortedData = useMemo(() => {
      if (!sortConfig.key) return filteredData;
      
      return [...filteredData].sort((a,b)=>{
        if (a[sortConfig.key] < b[sortConfig.key]){
          return sortConfig.direction === 'asc' ? -1 : 1;
        }
        if (a[sortConfig.key] > b[sortConfig.key]){
          return sortConfig.direction === 'asc' ? 1 : -1;
        }
        return 0;
      })

  },[filteredData, sortConfig]);
   
  const totalPages = Math.ceil(filteredData.length / recordsPerPage );
  const startIndex = (currentPages - 1) * recordsPerPage;
  const currentRecords = sortedData.slice(startIndex, startIndex + recordsPerPage);

  const getSortIcon = (field) => {
    if (sortConfig.key === field ){
       return sortConfig.direction === 'asc' ? ARROW_UP : ARROW_DOWN;
    }
    return'';
  }

  const handlePagesChange = (pageNumber)=>{
      setCurrenPages(pageNumber);
  }

  const handleRecordsPerPageChange = (e) => {
     setRecordsPerPage(Number(e.target.value));
     setCurrenPages(1)
  } 
 

  return (
    <Fragment>
      <SearchBar 
        searchTerm = {searchTerm}
        setSearchTerm={setSearchTerm}
        reacordsPerPage={recordsPerPage}
        handleRecordsPerPageChange={handleRecordsPerPageChange}
      />
      <div id='no_more_table'>
        <Table>
           <TableHeader 
             headers={headers}
             handleSort={handleSort}
             getSortIcon={getSortIcon}
           />
           <TableBody 
                headers={headers}
                currentRecords={currentRecords} 
                path={path}
           />
        </Table>
        <div className='container-pagination'>
             <PaginationInfo 
                page={currentPages}
                pageSize={recordsPerPage}
                totalElements={filteredData.length}
             />
             <Pagination 
                currentPage={currentPages}
                totalPages={totalPages}
                onPageChange={handlePagesChange}
             />
        </div>
        <div className='row mt-4'>
          <div className='col-xs-12 col-sm-12 col-md-3 ml-5'>
              <LinkButton 
                 to={`/${path}/incluir`} 
                 type='button'
                 title={`incluir dados - ${path}`}
                 variant={PRIMARY}
                 cssClass="app-label app-button"
                 label="Incluir "
                 icon={<FaIcons.FaPlus size={BUTTON_SIZE}/>}
              />
          </div>
        </div>
      </div> 
    </Fragment>
  )
}

export default TabelaPaginadaCliente
