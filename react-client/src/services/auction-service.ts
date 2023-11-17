import axios from 'axios';

const baseUrl = process.env.baseUrl;

async function generateAuctionDetails() {
  axios.get('http://localhost:8080/auction', {});
}
