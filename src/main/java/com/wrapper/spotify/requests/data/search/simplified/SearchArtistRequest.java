package com.wrapper.spotify.requests.data.search.simplified;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.Artist;
import com.wrapper.spotify.model_objects.Paging;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class SearchArtistRequest extends AbstractDataRequest {

  private SearchArtistRequest(final Builder builder) {
    super(builder);
  }

  public Paging<Artist> get() throws
          IOException,
          NoContentException,
          BadRequestException,
          UnauthorizedException,
          ForbiddenException,
          NotFoundException,
          TooManyRequestsException,
          InternalServerErrorException,
          BadGatewayException,
          ServiceUnavailableException {
    return new Artist.JsonUtil().createModelObjectPaging(getJson(), "artists");
  }

  public SettableFuture<Paging<Artist>> getAsync() throws
          IOException,
          NoContentException,
          BadRequestException,
          UnauthorizedException,
          ForbiddenException,
          NotFoundException,
          TooManyRequestsException,
          InternalServerErrorException,
          BadGatewayException,
          ServiceUnavailableException {
    return executeAsync(new Artist.JsonUtil().createModelObjectPaging(getJson(), "artists"));
  }

  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    public Builder(final String accessToken) {
      super(accessToken);
    }

    public Builder query(final String query) {
      assert (query != null);
      setPath("/v1/search");
      setFormParameter("type", "artist");
      return setFormParameter("q", query);
    }

    public Builder market(final String market) {
      assert (market != null);
      return setFormParameter("market", market);
    }

    public Builder limit(final Integer limit) {
      assert (1 <= limit && limit <= 50);
      return setFormParameter("limit", limit);
    }

    public Builder offset(final Integer offset) {
      assert (offset >= 0);
      return setFormParameter("offset", offset);
    }

    @Override
    public SearchArtistRequest build() {
      return new SearchArtistRequest(this);
    }

  }
}
