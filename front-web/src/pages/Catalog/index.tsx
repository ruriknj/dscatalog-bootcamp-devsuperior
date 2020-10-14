import React, { useEffect } from 'react';
import { Link } from 'react-router-dom';
import ProductCard from './components/productCard';
import './styles.scss';

const Catalog = () => {

/* quando o componente iniciar, buscar a lista de produtos;
   quando a lista de produtos estiver disponível,
   popular um estado no componente, e listar os produtos dinamicamente
 */

    useEffect(() => {
        fetch('http://localhost:3000/products')
            .then(Response => Response.json() )
            .then(Response => console.log(Response) )
    }, []);

    return (
        <div className="catalog-container">
            <h1 className="catalog-title">
                Catálogo de produtos
        </h1>
            <div className="catalog-products">
                <Link to="/products/1">
                    <ProductCard /></Link>
                <Link to="/products/2">
                    <ProductCard /></Link>
                <Link to="/products/4">
                    <ProductCard /></Link>
                <Link to="/products/5">
                    <ProductCard /></Link>
                <Link to="/products/6">
                    <ProductCard /></Link>
                <Link to="/products/7">
                    <ProductCard /></Link>
                <Link to="/products/8">
                    <ProductCard /></Link>
                <Link to="/products/9">
                    <ProductCard /></Link>
                <Link to="/products/10">
                    <ProductCard /></Link>
            </div>
        </div>
    );
}

export default Catalog;