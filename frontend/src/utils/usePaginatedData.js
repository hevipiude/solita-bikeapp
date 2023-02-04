import axios from 'axios'
import { useState, useEffect } from 'react'

const usePaginatedData = (route) => {
  const [content, setContent] = useState([])

  const [count, setCount] = useState(0)
  const [page, setPage] = useState(0)
  const [rowsPerPage, setRowsPerPage] = useState(10)

  const [loading, setLoading] = useState(true)

  useEffect(() => {
    axios
      .get(
        `http://localhost:8080/${route}?pageNumber=${page}&pageSize=${rowsPerPage}`
      )
      .then((res) => {
        setContent(res.data.content)
        setCount(res.data.totalElements)
        setLoading(false)
      })
      .catch((err) => console.error(err))
  }, [page, rowsPerPage])

  const onPageChange = (_, newPage) => {
    setPage(newPage)
  }

  const onRowsPerPageChange = (event) => {
    setRowsPerPage(event.target.value)
    setPage(0)
  }

  return {
    content,
    loading,
    paginationProps: {
      component: 'div',
      rowsPerPageOptions: [5, 10, 25],
      count,
      rowsPerPage,
      page,
      onPageChange,
      onRowsPerPageChange,
    },
  }
}

export default usePaginatedData
