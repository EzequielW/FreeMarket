interface Order{
    id: number,
    customer: string,
    total_products: number,
    total_price: number,
    created_at: string,
    status: string,
}

export default Order;