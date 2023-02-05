import axios from 'axios'
import { useState, useEffect } from 'react'

const usePaginatedData = (route) => {
  const [content, setContent] = useState([])
  const [sort, setSort] = useState('')
  const [order, setOrder] = useState('')
  const [count, setCount] = useState(0)
  const [page, setPage] = useState(0)
  const [rowsPerPage, setRowsPerPage] = useState(10)
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    setLoading(true)
    axios
      .get(
        `http://localhost:8080/${route}?pageNumber=${page}&pageSize=${rowsPerPage}&sort=${sort}&order=${order}`
      )
      .then((res) => {
        setContent(res.data.content)
        setCount(res.data.totalElements)
        setLoading(false)
      })
      .catch((err) => console.error(err))
  }, [page, rowsPerPage, sort, order])

  const onPageChange = (_, newPage) => {
    setPage(newPage)
  }

  const onRowsPerPageChange = (event) => {
    setRowsPerPage(event.target.value)
    setPage(0)
  }

  const onSortChange = (newSort) => {
    setSort(newSort)
  }

  const onOrderChange = (newOrder) => {
    setOrder(newOrder)
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
    sortingProps: {
      sort,
      order,
      onSortChange,
      onOrderChange,
    },
  }
}

export default usePaginatedData
