import { UserAuctionDto } from '@/types/userTypes';

type MediaDto = {
  id: number;
  imageUrl: string;
  extension: string;
  auction: AuctionDto | null;
};

type AuctionDto = {
  id?: number;
  startDate: Date;
  endDate: Date;
  localization: string;
};

type AuctionDetailsDto = {
  auctionCoreInformation: AuctionCoreInformationDto;
  tagNames: string[];
};

type AuctionCoreInformationDto = {
  title: string;
  description: string;
  price: number;
};

type AuctionCreationDto = {
  id?: number;
  startDate: Date;
  endDate: Date;
  localization: string;
  tags: TagDto[];
  auctionCoreInformation: AuctionCoreInformationDto;
  media: MediaDto;
};

type MainPageAuctionDto = {
  imageUrl: string;
  title: string;
  description: string;
  timeLeft: TimeLeft;
  biggestBid: number;
  bidAuctionInfo: BidAuctionInfoDto;
};

type TagDto = {
  name: string;
};

type TimeLeft = {
  days: number;
  hours: number;
  minutes: number;
  seconds: number;
};

type BidAuctionInfoDto = {
  highestBid: number;
  numberOfBidders: number;
  topBiddersInfo: UserAuctionDto[];
};

export type { MediaDto, AuctionDetailsDto, AuctionDto, AuctionCreationDto, TagDto, MainPageAuctionDto };
