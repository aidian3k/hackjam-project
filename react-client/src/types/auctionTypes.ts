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

type TagDto = {
  name: string;
};

export type { MediaDto, AuctionDetailsDto, AuctionDto, AuctionCreationDto, TagDto };
