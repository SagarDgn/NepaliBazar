
import React from 'react'
import ProductList from '../components/ProductList'

const Home = () => {
  return (
    <div>
      <div>
        {/* <img src="village.png" className='mx-72 px-8'/> */}
      </div>
      <ProductList limit={4}/>
    </div>
  )
}

export default Home