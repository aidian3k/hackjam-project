import axios from 'axios';

const baseUrl = process.env.BASE_BACKEND_URL ? process.env.BASE_BACKEND_URL : '';

async function generateAuctionDetails(data: FormData) {
  return await axios.post(baseUrl, data).then(response => response.data);
}

export { generateAuctionDetails };
