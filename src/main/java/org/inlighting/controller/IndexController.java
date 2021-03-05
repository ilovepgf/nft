package org.inlighting.controller;

import org.inlighting.common.Msg;
import org.inlighting.entity.po.Nfts;
import org.inlighting.entity.query.LabelsQuery;
import org.inlighting.entity.query.NftsQuery;
import org.inlighting.entity.query.UserQuery;
import org.inlighting.service.ICatalogService;
import org.inlighting.service.ILabelsService;
import org.inlighting.service.INftsService;
import org.inlighting.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index")
public class IndexController {
	
	@Autowired
	INftsService nftsService;
	@Autowired
	IUserService userService;
	@Autowired
	ICatalogService catalogService;
	@Autowired
	ILabelsService labelsService;

	@PostMapping("/drop")
    public Msg drop(NftsQuery nfts) {
		Msg msg=nftsService.getDrop(nfts);
		return msg;
	}
	
	@PostMapping("/arties")
    public Msg arties(UserQuery query) {
		Msg msg=userService.arties(query);
		return msg;
	}
	
	@PostMapping("/catalogs")
    public Msg catalogs() {
		Msg msg=catalogService.catalogs();
		return msg;
	}
	
	@PostMapping("/nfts")
    public Msg nfts(NftsQuery nfts) {
		Msg msg=nftsService.nfts(nfts);
		return msg;
	}
	
	@PostMapping("/like")
    public Msg like(Nfts nfts) {
		Msg msg=nftsService.like(nfts.getId());
		return msg;
	}
	
	@PostMapping("/find")
    public Msg find() {
		return null;
	}
	
	@PostMapping("/hots")
    public Msg hots() {
		Msg msg=userService.hots();
		return msg;
	}
	
	@PostMapping("/tags")
    public Msg tags(LabelsQuery query) {
		Msg msg=labelsService.tags(query);
		return msg;
	}
}
