type MediaDto = {
  id: number;
  imageUrl: string;
  extension: string;
  auction: AuctionDto | null;
};

type AuctionDto = {
  id: number;
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

export type { MediaDto, AuctionDetailsDto };
