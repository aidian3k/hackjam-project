import axios from 'axios';
import { AuctionDetailsDto, MediaDto } from '@/types/auctionTypes';

const baseUrl = 'http://localhost:8080';

async function saveImage(data: FormData) {
  return await axios.post<MediaDto>(`${baseUrl}/api/upload-image`, data).then(response => response.data);
}

async function generateAuctionDetails(media: MediaDto) {
  return await axios.post<AuctionDetailsDto>(`${baseUrl}/api/auction/ai`, media).then(response => response.data);
}

export { saveImage, generateAuctionDetails };
