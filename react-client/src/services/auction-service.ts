import axios from 'axios';
import { AuctionCreationDto, AuctionDetailsDto, AuctionDto, MediaDto } from '@/types/auctionTypes';

const baseUrl = 'http://localhost:8080';

async function saveImage(data: FormData) {
  return await axios.post<MediaDto>(`${baseUrl}/api/upload-image`, data).then(response => response.data);
}

async function generateAuctionDetails(media: MediaDto) {
  return await axios.post<AuctionDetailsDto>(`${baseUrl}/api/auction/ai`, media).then(response => response.data);
}

async function addAuction(auctionCreationDto: AuctionCreationDto) {
  const defaultUserId = 1;
  return await axios.post<AuctionDto>(`${baseUrl}/api/auction/${defaultUserId}`, auctionCreationDto).then(response => response.data);
}

export { saveImage, generateAuctionDetails, addAuction };
